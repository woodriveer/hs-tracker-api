echo "Starting to create dynamo table"
awslocal dynamodb create-table \
    --table-name HsTracker \
    --attribute-definitions AttributeName=UserId,AttributeType=S AttributeName=Type,AttributeType=S \
    --key-schema AttributeName=UserId,KeyType=HASH AttributeName=Type,KeyType=RANGE \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5