import jenkins.model.*
import hudson.security.*

import com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl
import com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey

def updateUsernamePasswordCredentials = { username, new_password ->

    def creds = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials( com.cloudbees.plugins.credentials.common.StandardUsernameCredentials.class,Jenkins.instance)

    def c = creds.findResult { it.username == username ? it : null }

    if ( c ) {
        println "found credential ${c.id} for username ${c.username}"

        def credentials_store = Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()

        def result = credentials_store.updateCredentials(com.cloudbees.plugins.credentials.domains.Domain.global(),c,
            	new UsernamePasswordCredentialsImpl(c.scope, c.id, c.description, c.username, new_password) )

        if (result) {
            println "password changed for ${username}"
        } else {
            println "failed to change password for ${username}"
        }
    } else {
      println "could not find credential for ${username}"
    }
}

def updateUsernamePasswordCredentialsB64 = { username, new_password_b64 ->
	byte[] decoded = new_password_b64.decodeBase64()
	def new_password = new String(decoded)
    updateUsernamePasswordCredentials(username, new_password)
}

def updateSSHUserPassword = { username, new_password ->

    def creds = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials( BasicSSHUserPrivateKey.class,Jenkins.instance)

    def c = creds.findResult { it.username == username ? it : null }

    if ( c ) {
        println "found credential ${c.id} for username ${c.username}"

        def credentials_store = Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()
        println c.privateKeySource
        def newKey = new BasicSSHUserPrivateKey(c.scope, c.id, c.username,c.privateKeySource, new_password, c.description)

        def result = credentials_store.updateCredentials(com.cloudbees.plugins.credentials.domains.Domain.global(),c, newKey )

        if (result) {
            println "password changed for ${username}"
        } else {
            println "failed to change password for ${username}"
        }
    }
     else {
      println "could not find credential for ${username}"
    }
}

def updateSSHUserPasswordB64 = { username, new_password_b64 ->
	byte[] decoded = new_password_b64.decodeBase64()
	def new_password = new String(decoded)
    updateSSHUserPassword(username, new_password)
}

def createAdminAccount = { username, password ->
	def instance = Jenkins.getInstance()
	def hudsonRealm = new HudsonPrivateSecurityRealm(false)
	hudsonRealm.createAccount(username,password)
	instance.setSecurityRealm(hudsonRealm)
	instance.save()

	def strategy = new GlobalMatrixAuthorizationStrategy()
	strategy.add(Jenkins.ADMINISTER, username)
	instance.setAuthorizationStrategy(strategy)
	println "Added new administratror user and enabled security for Jenkins: " + username
}

def createAdminAccountB64 = { username, new_password_b64 ->
	byte[] decoded = new_password_b64.decodeBase64()
	def password = new String(decoded)
	createAdminAccount(username, password)
}

// *****************************************************************************
// Create a user and update the password.
// *****************************************************************************

createAdminAccountB64("user","dXNlcg==");
