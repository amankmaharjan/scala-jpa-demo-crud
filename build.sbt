name := "jdbc_demo"
 
version := "1.0" 
      
lazy val `jdbc_demo` = (project in file(".")).enablePlugins(PlayScala,PlayEbean)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

libraryDependencies += jdbc
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"
libraryDependencies ++= Seq(
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final" // replace by your jpa implementation
)
// https://mvnrepository.com/artifact/io.ebean/ebean
//libraryDependencies += "io.ebean" % "ebean" % "11.23.1"
libraryDependencies += javaForms