package com.example.parkingowner;

import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class UserRegister extends Activity {
	// �û��������
		private EditText editTextUserName;
		// �û����������
		private EditText editTextUserPass;
		// �û�����ȷ�Ͽ�
		private EditText editTextUserPassConfirm;
		// �û�ע���ֻ�
		private EditText editTextUserRegisterPhone;
		// ȷ��ע��
		private Button buttonLogin;
		// ȡ��ע��
		private Button buttonCancel;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			// ��ʼ������
			initWindows();

			setContentView(R.layout.user_register);
			// ��ʼ���ؼ�
			initWidget();
		}
		/**
		 * ʧȥ���棬������
		 */
		@Override
		protected void onStop() {
			super.onStop();
			UserRegister.this.finish();
			
		}

		/**
		 * 
		 * @Title: initWidget
		 * @Description: (��ʼ���ؼ�)
		 * @param �趨�ļ�
		 * @return void ��������
		 * @throws
		 * 
		 */
		private void initWidget() {
			editTextUserName = (EditText) findViewById(R.id.userLoginNameTxt);
			editTextUserPass = (EditText) findViewById(R.id.userPwdTxt);
			editTextUserPassConfirm = (EditText) findViewById(R.id.user_pwdconfirmTxt);
			editTextUserRegisterPhone = (EditText) findViewById(R.id.userPhoneTxt);
			buttonLogin = (Button) findViewById(R.id.bt_reg_ok);
			buttonCancel = (Button) findViewById(R.id.bt_reg_cancel);

			buttonCancel.setOnClickListener(new OnClickListener() {
				// ȡ��ע�ᣬ���ص�¼ҳ��
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(UserRegister.this,
							MainLoginActivity.class);
					startActivity(intent);

				}
			});
			/**
			 * ��ע����Ӽ���
			 * 
			 */
			buttonLogin.setOnClickListener(new OnClickListener() {

				private String tag = "TestLogin";

				@Override
				public void onClick(View v) {
					String userName = editTextUserName.getText().toString().trim();
					String userPwd = editTextUserPass.getText().toString().trim();
					String userPwdConfirm = editTextUserPassConfirm.getText()
							.toString().trim();
					String userPhone = editTextUserRegisterPhone.getText()
							.toString().trim();
					String userPwdUpdate = null;
					if (userName.isEmpty()) {
						Toast.makeText(UserRegister.this, "�û�������Ϊ��",
								Toast.LENGTH_SHORT).show();
						return;
					}
					if (userPwd.isEmpty() || userPwdConfirm.isEmpty()) {
						Toast.makeText(UserRegister.this, "���벻��Ϊ��",
								Toast.LENGTH_SHORT).show();
						return;
					}
					if (userPhone.isEmpty()) {
						Toast.makeText(UserRegister.this, "ע���ֻ��Ų���Ϊ��",
								Toast.LENGTH_SHORT).show();
						return;
					}
					if (!userPwd.equals(userPwdConfirm)) {
						Toast.makeText(UserRegister.this, "���벻һ��",
								Toast.LENGTH_SHORT).show();
						return;
					} else {
						// ������ת����MD5
						try {
							userPwdUpdate = MD5Util.getMD5(userPwd);
						} catch (NoSuchAlgorithmException e) {
							e.printStackTrace();
							return;
						}
						User user=new User(userName,userPwdUpdate,userPhone);
						
						//�����ݴ洢����������
						/*RegDataSave(user);*/
					}

					
				}

				/**
				 * 
				 * @Title: RegDataSave
				 * @Description: ʵ�����ݴ洢����������
				 * @param @param userName
				 * @param @param userPhone
				 * @param @param userPwdUpdate �趨�ļ�
				 * @return void ��������
				 * @throws
				 * 
				 */
			/*	private void RegDataSave(User user) {
					AVObject userInfoReg = new AVObject("UserInfo");
					userInfoReg.put("userName", user.getUserName());
					userInfoReg.put("userPwdMD5", user.getUserPwdMD5());
					userInfoReg.put("userPhone", user.getUserPwdMD5());
					userInfoReg.saveInBackground(new SaveCallback() {
						// TODOδ��Ҫ����һ���û���Ψһ����֤
						@Override
						public void done(AVException e) {
							if (e == null) {

								Log.v(tag, "ע��ɹ�");
								// ��ת����¼����
								Intent intent = new Intent(UserRegister.this,
										MainLoginActivity.class);
								
								startActivity(intent);
							} else {
								Toast.makeText(UserRegister.this, "ע��û�ɹ�",
										Toast.LENGTH_SHORT).show();
								return;
							}
						}
					});
				}*/
			});
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			getMenuInflater().inflate(R.menu.user_register, menu);
			return true;
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			int id = item.getItemId();
			if (id == R.id.action_settings) {
				return true;
			}
			return super.onOptionsItemSelected(item);
		}

		/**
		 * 
		 * @Title: initWindows
		 * @Description: (��ʼ������)
		 * @param �趨�ļ�
		 * @return void ��������
		 * @throws
		 */
		private void initWindows() {
			// ȥ����������������setCOntentView֮ǰ����
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			// ��һ����������Ҫ��ӵ��µĴ������Եı�־λ���൱��ֵ��
			// �ڶ��� �����Ǵ��ڵ���һ�����Ա�־λ��Ҫ�޸ģ��൱�ڿ��أ�
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
}
