resolvers += "BinTray sbt-deploy-ssh" at "https://bintray.com/shmishleniy/sbt-plugins/sbt-deploy-ssh/"
resolvers += "Local Maven Repository" at Path.userHome.asFile.toURI.toURL + ".m2/repository"

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.25")
addSbtPlugin("riot" % "sbt-riotctl" % "0.1-SNAPSHOT")
addSbtPlugin("com.github.shmishleniy" % "sbt-deploy-ssh" % "0.1.4")
