# SpringAccounting
Spring application for handling cash transfers

An account seen by a REST client has the following details:
- Name: String
- Currency: Currency
- Balance: Money
- Treasury: Boolean

The requirements are the following:
- Treasury property can be set only at creation time.
- Only treasury accounts (Treasury property) can have a negative balance.
- Non treasury accounts should block transfers if there is not enough balance.
- Unit and component testing (in progress)

Used DB: H2 in memory
