package com.pingunaut.tools.honeypot;


import java.io.IOException;

import org.apache.sshd.SshServer;
import org.apache.sshd.server.PasswordAuthenticator;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.session.ServerSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SSHServer {
	
	public static int LISTENING_PORT = 4000;
	
	public static void main(String[]args){
		
		if(args.length==1){
			LISTENING_PORT = Integer.valueOf(args[0]);
		}
		
		SshServer sshd = SshServer.setUpDefaultServer();
		sshd.setPort(LISTENING_PORT);
		sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider("hostkey.ser"));
		PasswordAuthenticator passwordAuthenticator = new PasswordAuthenticator(){
			@Override
			public boolean authenticate(String username, String password, ServerSession session) {
				String msg = "Login try recorded: [user: "+ username + ", password: "+password 
						+ ", client: "+session.getClientVersion() + ", source: "+session.getIoSession().getRemoteAddress()+"]";
				Logger log = LoggerFactory.getLogger(SSHServer.class);
				log.info(msg);
				return false;
			}
		};
		sshd.setPasswordAuthenticator(passwordAuthenticator );
		try {
			sshd.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
