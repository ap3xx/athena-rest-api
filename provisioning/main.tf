provider "aws" {
  version = "~> 2.0"
  region = "${var.aws_region}"
}

resource "aws_s3_bucket" "datalake_bucket" {
  bucket = "${var.datalake_bucket}"
  acl    = "private"

  tags = {
    Name        = "data lake"
    Environment = "${var.env}"
  }
}

resource "aws_s3_bucket" "query_results_bucket" {
  bucket = "${var.query_results_bucket}"
  acl    = "private"

  tags = {
    Name        = "athena query results"
    Environment = "${var.env}"
  }
}

resource "aws_glue_catalog_database" "athena_database" {
  name = "${var.athena_database_name}"
}

resource "aws_s3_bucket_object" "orders" {
  depends_on=["aws_s3_bucket.datalake_bucket"]
  bucket = "${var.datalake_bucket}"
  key    = "orders/orders.parquet"
  source = "sample/orders.parquet"
}

resource "aws_s3_bucket_object" "restaurants" {
  depends_on=["aws_s3_bucket.datalake_bucket"]
  bucket = "${var.datalake_bucket}"
  key    = "restaurants/restaurants.parquet"
  source = "sample/restaurants.parquet"
}

resource "aws_glue_catalog_table" "orders_table" {
  name          = "orders"
  database_name = "${aws_glue_catalog_database.athena_database.name}"

  table_type = "EXTERNAL_TABLE"

  parameters = {
    EXTERNAL              = "TRUE"
    "parquet.compression" = "SNAPPY"
  }

  storage_descriptor {
    location      = "s3://${var.datalake_bucket}/orders/"
    input_format  = "org.apache.hadoop.hive.ql.io.parquet.MapredParquetInputFormat"
    output_format = "org.apache.hadoop.hive.ql.io.parquet.MapredParquetOutputFormat"

    ser_de_info {
      name = "ParquetSerDe"
      serialization_library = "org.apache.hadoop.hive.ql.io.parquet.serde.ParquetHiveSerDe"
    }

    columns {
      name = "id"
      type = "string"
    }

    columns {
      name = "restaurant_id"
      type = "int"
    }

    columns {
      name = "customer_id"
      type = "int"
    }

    columns {
      name = "total_value"
      type = "double"
    }

    columns {
      name    = "date"
      type    = "timestamp"
    }
    
  }
}

resource "aws_glue_catalog_table" "restaurants_table" {
  name          = "restaurants"
  database_name = "${aws_glue_catalog_database.athena_database.name}"

  table_type = "EXTERNAL_TABLE"

  parameters = {
    EXTERNAL              = "TRUE"
    "parquet.compression" = "SNAPPY"
  }

  storage_descriptor {
    location      = "s3://${var.datalake_bucket}/restaurants/"
    input_format  = "org.apache.hadoop.hive.ql.io.parquet.MapredParquetInputFormat"
    output_format = "org.apache.hadoop.hive.ql.io.parquet.MapredParquetOutputFormat"

    ser_de_info {
      name = "ParquetSerDe"
      serialization_library = "org.apache.hadoop.hive.ql.io.parquet.serde.ParquetHiveSerDe"
    }

    columns {
      name = "id"
      type = "int"
    }

    columns {
      name = "name"
      type = "string"
    }

    columns {
      name    = "state"
      type    = "string"
    }

  }

}
