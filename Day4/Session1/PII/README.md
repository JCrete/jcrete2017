# PII - personally identifiable information
Conventor: Haim

* Name phone address etc
* If you encrypt everything than BI can't use it
* RDS Amazon store encrypted 
* Kms amazon manages keys, master key can only be accessed by certain machines. Generate data key from master
* Rencrypt all the data when keys change with batch and using both keys throughout the process
* GDPR general data protection regulations 
* Problem searching for full name: maybe store hashed data without salt
* Rehashing mechanism necessary? Data can be correlated and if you reach the database and can store data you can find out the hashes
* Low level data accces layer encrypt and decrypt
* HSM is more expensive
* Keycloak has more features now
* When you have json which goes into the database
* Logging statements: make sure to string is not called on unencrypted data
* Zero knowledge Protocols (signal)