package com.example.parkingowner;

import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.user.bean.User;
import com.util.MD5Util;
import com.util.NetConnectionUtil;

public class MainLoginActivity extends Activity {

	/**
	 * ��¼���û���
	 */
	private String userName = null;
	/**
	 * ��¼���루MD5���ܺ�
	 */
	private String userPwd = null;
	/**
	 * �û���¼�û�����
	 */
	private EditText editText;
	/**
	 * �û���¼�����
	 */
	private EditText editPwd;
	// ��¼�ؼ�
	private Button buttonLogin;
	// ע��ؼ�
	private Button buttonReg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ��ʼ������
		initWindows();
		setContentView(R.layout.user_login_main);
		// ��ʼ���ؼ�
		initButton();
		// ����ť��Ӽ���
		addButtonListener();

	}

	/**
	 * 
	 * @Title: addButtonListener
	 * @Description:(����ť��Ӽ���)
	 * @param �趨�ļ�
	 * @return void ��������
	 * @throws
	 * 
	 */
	private void addButtonListener() {
		// ����¼��ť��Ӽ���
		buttonLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ������
				boolean isConnected = checkNetWork();
				if (!isConnected) {
					return;
				}
				// ��ȡ��¼��Ϣ
				getLoginInfo();
				// ����¼��Ϣ�Ƿ�����
				boolean dataIsOk = checkLoginInfo();
				if (dataIsOk) {
					// ���û�������ʾ��
					showTip("�û����������벻��Ϊ��");
					return;
				}
				// ���û�������MD5����
				try {
					userPwd = MD5Util.getMD5(userPwd);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				//��װ��¼��Ϣ
				User user = setLoginInfo();
				// ��¼��֤
				/*LoginQuery(user);*/

			}
			/**
			 * 
			 * @Title: setLoginInfo 
			 * @Description: ��װ��¼��Ϣ
			 * @param @return    �趨�ļ� 
			 * @return User    ��װ���û��ύ��������userBean
			 * @throws 
			**/
			private User setLoginInfo() {
				User user = new User();
				user.setUserName(userName);
				user.setUserPwdMD5(userPwd);
				return user;
			}

			/**
			 * 
			 * @Title: getLoginInfo
			 * @Description: �ӽ����ȡ��¼��Ϣ
			 * @param �趨�ļ�
			 * @return void ��������
			 * @throws
			 **/
			private void getLoginInfo() {
				userName = editText.getText().toString().trim();
				userPwd = editPwd.getText().toString().trim();
			}

			/**
			 * 
			 * @Title: checkLoginInfo
			 * @Description: ����û������Ƿ���Ч
			 * @param @return �趨�ļ�
			 * @return boolean �����
			 * @throws
			 * 
			 */
			private boolean checkLoginInfo() {
				boolean dataIsOk = userName.isEmpty() || userPwd.isEmpty();
				return dataIsOk;
			}

			/**
			 * 
			 * @Title: showTip
			 * @Description: ���û������ʾ
			 * @param @param Tips �������ʾ����
			 * @return void ��������
			 * @throws
			 * 
			 */
			private void showTip(String Tips) {
				Toast.makeText(MainLoginActivity.this, Tips, Toast.LENGTH_SHORT)
						.show();
			}

			/**
			 * 
			 * @Title: checkNetWork
			 * @Description: ����������ӵķ���
			 * @param @return �趨�ļ�
			 * @return boolean �Ƿ����ӳɹ�
			 * @throws
			 * 
			 */
			private boolean checkNetWork() {
				boolean isConnect = NetConnectionUtil
						.checkNetworkAvailable(MainLoginActivity.this);
				if (!isConnect) {
					// �����ʾ��
					showTip("����������");
					return false;
				} else {
					return true;
				}
			}

			/**
			 * 
			 * @Title: LoginQuery
			 * @Description: �û���¼��֤
			 * @param @param user ��֤�û�bean
			 * @return void ��������
			 * @throws
			 * 
			 */
			/*private void LoginQuery(User user) {
				AVQuery<AVObject> query = new AVQuery<AVObject>("UserInfo");
				query.whereEqualTo("userName", user.getUserName());
				query.whereEqualTo("userPwdMD5", user.getUserPwdMD5());
				query.findInBackground(new FindCallback<AVObject>() {

					@Override
					public void done(List<AVObject> list, AVException e) {
						if (list.size() != 0) {
							// TODO������ܼ�¼�û���¼��Ϣ��
							Intent intent = new Intent(MainLoginActivity.this,
									MainUI.class);
							startActivity(intent);
						} else {
							editText.setText("");
							editPwd.setText("");
						}
					}
				});
			}*/
		});
		// ע�ᰴť�ļ���
		buttonReg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��ת��ע�����
				Intent intent = new Intent(MainLoginActivity.this,
						UserRegister.class);
				startActivity(intent);
			}
		});
	}

	/**
	 * 
	 * @Title: initWindows
	 * @Description:(��ʼ������)
	 * @param �趨�ļ�
	 * @return void ��������
	 * @throws
	 * 
	 */
	private void initWindows() {
		// ȥ����������������setCOntentView֮ǰ����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ��һ����������Ҫ��ӵ��µĴ������Եı�־λ���൱��ֵ��
		// �ڶ��� �����Ǵ��ڵ���һ�����Ա�־λ��Ҫ�޸ģ��൱�ڿ��أ�
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	/**
	 * 
	 * @Title: initButton
	 * @Description:(��ʼ�����û���¼������򣬵�¼��ע��İ�ť)
	 * @param �趨�ļ�
	 * @return void ��������
	 * @throws
	 */
	private void initButton() {
		editText = (EditText) findViewById(R.id.userLoginNameTxt);
		editPwd = (EditText) findViewById(R.id.userPwdTxt);
		buttonLogin = (Button) findViewById(R.id.btLogin);
		buttonReg = (Button) findViewById(R.id.btRegist);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onStop() {
		super.onStop();
		MainLoginActivity.this.finish();
	}
}
