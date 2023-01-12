# rapid-pay

Introducing RapidPay, the ultimate rapidPayment processing solution for your business. With our powerful and easy-to-use platform, you can quickly and securely process payments from customers using a variety of rapidPayment methods, including credit cards, bank transfers, and more.

Our platform is built on top of the Stripe rapidPayment processing engine, which is known for its reliability and security. We leverage the power of Stripe to provide you with a fast, flexible and secure rapidPayment processing solution that is easy to integrate with your existing systems.

RapidPay provides you with a comprehensive suite of features that allow you to manage your payments with ease. You can create, retrieve, update and delete payments, customers and rapidPayment methods as well as issue refunds and cancel payments. Our platform also provides you with detailed analytics and reporting, so you can monitor your transactions and track your revenue in real-time.

Our platform is designed to be highly scalable and customizable, so you can easily adapt it to meet the needs of your business. And with our easy-to-use API, you can integrate RapidPay with your existing systems and applications with minimal effort.

We understand the importance of security, that's why we have implemented a robust security measures to protect your transactions and rapidCustomer data. Our platform is PCI-compliant, which means that it meets the highest standards for security and data protection.

RapidPay is built on top of Quarkus, a Kubernetes-native Java stack that reduces startup time and memory footprint, making it ideal for cloud-native deployments and microservices.

With RapidPay, you can focus on growing your business, while we take care of the rest. Sign up today and start accepting payments in minutes!

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/rapid-pay-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- REST resources for Hibernate ORM with Panache ([guide](https://quarkus.io/guides/rest-data-panache)): Generate JAX-RS
  resources for your Hibernate Panache entities and repositories
- Hibernate Validator ([guide](https://quarkus.io/guides/validation)): Validate object properties (field, getter) and
  method parameters for your beans (REST, CDI, JPA)
- RESTEasy Classic JSON-B ([guide](https://quarkus.io/guides/rest-json)): JSON-B serialization support for RESTEasy
  Classic
- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache)): Simplify your persistence code
  for Hibernate ORM via the active record or the repository pattern
- Blaze-Persistence ([guide](https://quarkus.io/guides/blaze-persistence)): Advanced SQL support for JPA and
  Entity-Views as efficient DTOs
- Hibernate Envers ([guide](https://quarkus.io/guides/hibernate-orm#envers)): Enable Hibernate Envers capabilities in
  your JPA applications

## Provided Code

### gRPC

Create your first gRPC service

[Related guide section...](https://quarkus.io/guides/grpc-getting-started)

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
