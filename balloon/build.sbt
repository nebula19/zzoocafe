name := "balloon"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)     


libraryDependencies ++= Seq(
	"mysql" % "mysql-connector-java" % "5.1.26",
	"com.google.code.gson" % "gson" % "2.2.4"
)


play.Project.playJavaSettings
