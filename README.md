
# Customer Rewards Calculator

This project calcualtes the rewards earned for the customer for every purchase (transaction). 

#### Project Set up
```'
mvn clean install
```
While bootstrap, it will load with sample data. You can use CUSTOMER_ID =1 to get response.


This project provides 2 API endpoints 

#### Endpoint :1 

This is a GET endpoint takes customer Id as input and returns rewards per month
```
/rewards/get/customer/{id}
```

##### Sample Response
```
{
    "customerId": 1,
    "customerName": "Adam",
    "rewardsByMonth": {
        "MAY": 90,
        "MARCH": 130,
        "APRIL": 110
    },
    "totalRewards": 330
}
```

#### Endpoint :2
This is a POST endpoint which takes customer Id, purchase (receipt) as input and calcualtes the rewards earned.

```
/rewards/new/purchase/customer/{id}
```

#### Sample Request

```
{
    "purchaseAmount" : 500,
    "purchasedTimeStamp" : "2022-05-20T02:59:00"
}
```

#### Tech
* Java 11
* Spring Boot (Default server Tomcat runs in 8080 port)
* lombok
* spring-validations
* Junit 5
* Mockito


