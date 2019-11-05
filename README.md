# moneytransfer

Money transfer service thorugh REST API, Creates accpunts and transfers money between two given account numbers.

This works on H2 in memory database.

Crate Account: This request will create an account based on API request

POST : http://localhost:8080/transaction/createAccount

REQUEST BODY:
{
	"accountNumber": 1,
	"balance":308,
	"name":"sd",
	"email":"rahul@gamil.com"
}

Transaction: Transaction service will give response based on the Source and Destination account number, with proper error message

POST : http://localhost:8080/transaction/createTransaction

REQUEST BODY:
{
	"destinationAccountNumber": 2,
	"sourceAccountNumber": 1,
	"amount":10
}

Delete Account: As an add on i have added Delete method which will delete account from H2 database.

DELETE: http://localhost:8080/transaction/{accountNumber}
