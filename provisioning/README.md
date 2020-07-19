# provisioning

This terraform scripts creates some structures at AWS:

* The bucket for parquet files.
* The sample data as parquet files.
* The bucket for Athena query results.
* AWS Glue database.
* AWS Glue tables for the two entities: restaurants and orders.

## Configuration

Check the file [terraform.tfvars.json](./terraform.tfvars.json) to configure the name of the buckets.

## How to run it

Run the following commands and follow the instructions:

```
terraform init
terraform apply
```