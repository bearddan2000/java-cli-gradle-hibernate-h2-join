# java-cli-gradle-hibernate-h2-join

## Description
Creates a small database table
called `dog` and populates with
hql. Creates 2 lookup tables `breedLookup`
and `colorLookup` and 4N `dog`.

Joins `breedLookup` and `colorLookup`
to form a new table `dogextended`. These
joins are deministrated 3 ways, `ResultTransformer`,
looking up ids on `breedLookup` and `colorLookup`,
and non-ResultTransformer method.

## Tech stack
- java
- gradle
  - hibernate
  - hql
  - log4j
  - h2 driver

## Docker stack
- gradle:jdk11

## To run
`sudo ./install.sh -u`

## To stop
`sudo ./install.sh -d`

## For help
`sudo ./install.sh -h`

## Credit
- [ResultTransformer code based on](https://thorben-janssen.com/hibernate-resulttransformer/)
- [HQL code based on](https://www.journaldev.com/2954/hibernate-query-language-hql-example-tutorial)
- [Hibernate config based on](https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/An-example-hibernatecfgxml-for-MySQL-8-and-Hibernate-5)
- [Hibernate code based on](https://github.com/lokeshgupta1981/hibernate/tree/master/hibernate-hello-world)
