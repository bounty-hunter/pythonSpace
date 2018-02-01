package trails.jsch;

import com.jcraft.jsch.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SftpFileSendTest {

    public static void main(String[] args) throws JSchException, SftpException, IOException {
        SftpFileSendTest obj = new SftpFileSendTest();
        obj.run();
    }

    public void run() throws JSchException, SftpException, IOException {

        String hostname = "192.168.188.128";
        String login = "rishi";
        String password = "rishi";
        String directory = "/home/rishi";

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");

        JSch ssh = new JSch();
        Session session = ssh.getSession(login, hostname, 22);
        session.setConfig(config);
        session.setPassword(password);
        session.connect();
        Channel channel = session.openChannel("sftp");
        channel.connect();

        File fileToSend = new File("C:\\Users\\deban\\Desktop\\something.txt");
        InputStream is = new FileInputStream(fileToSend);

        ChannelSftp sftp = (ChannelSftp) channel;
        sftp.put(is, "/home/rishi/test/something.txt");

        is.close();
        channel.disconnect();
        session.disconnect();

    }

}
