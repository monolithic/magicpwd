package test.mail;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class MailConstructor {
	// private String EnvelopeFrom;//邮件发送地址
	// private String MailHost;//邮件服务器
	private String UserName;// 用户名

	private String PassWord;// 用户密码

	private MimeMessage mimeMsg; // MIME邮件对象

	private Session session; // 邮件会话对象

	private Properties props; // 系统属性
	// private boolean needAuth = false; //smtp是否需要认证

	private Multipart mp; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象
	Authenticator auth;

	public MailConstructor() {
	}

	/**
	 * public void sendMail() {
	 * setSmtpHost(getConfig.mailHost);//如果没有指定邮件服务器,就从getConfig类中获取
	 * createMimeMessage(); }
	 */
	public MailConstructor(String smtp) {
		setSmtpHost(smtp);
		createMimeMessage();

	}

	/**
	 * @param hostName
	 *            String
	 */
	public void setSmtpHost(String hostName) {
		System.out.println("设置系统属性：mail.smtp.host = " + hostName);
		if (props == null)
			props = new Properties();//System.getProperties(); // 获得系统属性对象
		props.put("mail.smtp.host", hostName); // 设置SMTP主机

		auth = new Email_Autherticator();// 进行邮件服务器用户认证
		props.put("mail.smtp.auth", "true");

	}

	/**
	 * @return boolean
	 */
	public boolean createMimeMessage() {
		try {
			System.out.println("准备获取邮件会话对象！");
			session = Session.getDefaultInstance(props, auth); // 获得邮件会话对象
		} catch (Exception e) {
			System.err.println("获取邮件会话对象时发生错误！" + e);
			return false;
		}
		System.out.println("准备创建MIME邮件对象！");
		try {
			mimeMsg = new MimeMessage(session); // 创建MIME邮件对象
			mp = new MimeMultipart();
			return true;
		} catch (Exception e) {
			System.err.println("创建MIME邮件对象失败！" + e);
			return false;
		}
	}

	/**
	 * @param need
	 *            boolean
	 */
	public void setNeedAuth(boolean need) {
		System.out.println("设置smtp身份认证：mail.smtp.auth = " + need);
		if (props == null)
			props = new Properties();//System.getProperties();
		if (need) {
			props.put("mail.smtp.auth", "true");
		} else {
			props.put("mail.smtp.auth", "false");
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public void setNamePass(String name, String pass) {
		UserName = name;
		PassWord = pass;
	}

	/**
	 * @param mailSubject
	 *            String
	 * @return boolean
	 */
	public boolean setSubject(String mailSubject) {
		System.out.println("设置邮件主题！");
		try {
			mimeMsg.setSubject(mailSubject);
			return true;
		} catch (Exception e) {
			System.err.println("设置邮件主题发生错误！");
			return false;
		}
	}

	/**
	 * @param mailBody
	 *            String
	 */
	public boolean setBody(String mailBody) {
		try {
			BodyPart bp = new MimeBodyPart();
			bp.setContent(" <meta http-equiv=Content-Type content=text/html; charset=gb2312>" + mailBody, "text/html;charset=GB2312");
			mp.addBodyPart(bp);
			return true;
		} catch (Exception e) {
			System.err.println("设置邮件正文时发生错误！" + e);
			return false;
		}
	}

	public boolean addFileAffix(String filename) {
		System.out.println("增加邮件附件：" + filename);
		try {
			BodyPart bp = new MimeBodyPart();
			FileDataSource fileds = new FileDataSource(filename);
			bp.setDataHandler(new DataHandler(fileds));
			bp.setFileName(MimeUtility.encodeText("magicpwd.amb"));
			mp.addBodyPart(bp);
			return true;
		} catch (Exception e) {
			System.err.println("增加邮件附件：" + filename + "发生错误！" + e);
			return false;
		}
	}

	public boolean setFrom(String from) {
		System.out.println("设置发信人！");
		try {
			mimeMsg.setFrom(new InternetAddress(from)); // 设置发信人
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean setTo(String to) {
		if (to == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean setCopyTo(String copyto) {
		if (copyto == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.CC, (Address[]) InternetAddress.parse(copyto));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean sendout() {
		try {
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			System.out.println("正在发送邮件....");
			Session mailSession = Session.getInstance(props, auth);
			Transport transport = mailSession.getTransport("smtp");
			transport.connect((String) props.get("mail.smtp.host"), UserName, PassWord);

			transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
			// transport.send(mimeMsg);
			System.out.println("发送邮件成功！");
			transport.close();
			return true;
		} catch (Exception e) {
			System.err.println("邮件发送失败！" + e);
			return false;
		}
	}

	/**
	 * Just do it as this
	 */
	public static void main(String[] args) {
		String mailbody = " <meta http-equiv=Content-Type content=text/html; charset=gb2312>" + " <div align=center> <a href=http://www.csdn.net> 恭喜你，中奖啦！ </a> </div>";
		MailConstructor themail = new MailConstructor("smtp.163.com");
		themail.setNeedAuth(true);
		if (themail.setSubject("标题") == false)
			return;
		if (themail.setBody(mailbody) == false)
			return;
		if (themail.setFrom("Amon.CT@163.com") == false)
			return;
		if (themail.setTo("Amon.CT@163.com") == false)
			return;

		if (themail.setBody("恭喜你，你中一等奖啦！！！") == false)
			return;
//		if (themail.addFileAffix("F:\\Rmps\\MagicPwd\\bak\\amon_20100824-130014.backup") == false)
//			return;
		// if(themail.addFileAffix("c:\\boot.ini") == false) return;
		themail.setNamePass("Amon.CT@163.com", "bMjbmaG0zm");
		if (themail.sendout() == false)
			return;
	}

	public class Email_Autherticator extends Authenticator {// 此段代码用来进行服务器对用户的认证
		public Email_Autherticator() {
			super();
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(UserName, PassWord);
		}
	}

}