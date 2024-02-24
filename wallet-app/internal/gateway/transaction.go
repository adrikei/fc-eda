package gateway

import "wallet-app/internal/entity"

type TransactionGateway interface {
	Create(transaction *entity.Transaction) error
}
