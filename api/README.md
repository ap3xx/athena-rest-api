# api

The REST API itself.

## How to build it

I created a bash script that does all the work of packaging the jar and building the docker image. Just run it:

`bash build.sh`

Before building the package, the `src/main/resources/application.properties` file must be filled with the access and secret key of a user that has access to aws athena in the environment it will run, and the name of your query results bucket as well.

## How to run it

Just run with docker:

`docker run -d -p 8080:8080 ap3xx/api`

## How to use it

This API listens to port 8080 and has two endpoints:

`/metrics/topRestaurants/{customerId}`: This endpoint returns the top 10 restaurants for the desired customer.

Response:

```json
{
    "status": "success",
    "timestamp": "2020-04-12T21:07:16.466",
    "data": {
        "customerId": 1,
        "topRestaurants": [
            "Goodwin",
            "Soto",
            "Roy",
            "Morris",
            "Swanson",
            "Jimenez",
            "Padilla",
            "Horton",
            "Cain",
            "Hicks"
        ]
    }
}

```

`/metrics/ordersByState/{date}`: This endpoint returns the sum of orders by city and state for the desired date (yyyy/MM/dd).

Response:

```json
{
    "status": "success",
    "timestamp": "2020-04-12T21:02:12.362",
    "data": [
        {
            "state": "AZ",
            "orders": 279,
            "date": "2020-04-01"
        },
        {
            "state": "NV",
            "orders": 270,
            "date": "2020-04-01"
        },
        {
            "state": "CA",
            "orders": 451,
            "date": "2020-04-01"
        }
    ]
}
```