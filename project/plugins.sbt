resolvers += "Local Maven Repository" at Path.userHome.asFile.toURI.toURL + ".m2/repository"

addSbtPlugin("riot" % "sbt-riotctl" % "0.1-SNAPSHOT")
