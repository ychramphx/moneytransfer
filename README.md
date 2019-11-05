# moneytransfer

Money transfer service thorugh REST API, Creates accpunts and transfers money between two given account numbers

POST : http://localhost:8080/transaction/createAccount
REQUEST BODY:
{
	"accountNumber": 1,
	"balance":308,
	"name":"sd",
	"email":"rahul@gamil.com"
}


POST : http://localhost:8080/transaction/createTransaction
REQUEST BODY:
{
	"destinationAccountNumber": 2,
	"sourceAccountNumber": 1,
	"amount":10
}


DELETE: http://localhost:8080/transaction/{accountNumber}
