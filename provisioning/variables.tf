variable "env" {
  description = "Environment"
  default = "Dev"
}

variable "aws_region" {
  description = "The AWS region to create things in."
  default = "us-east-1"
}

variable "datalake_bucket" {
  description = "Datalake Bucket name"
}

variable "query_results_bucket" {
  description = "Query results Bucket name"
}

variable "athena_database_name" {
  description = "Athena Database name"
  default = "sample_database"
}
