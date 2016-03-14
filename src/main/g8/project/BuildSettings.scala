import sbt._
import sbt.Keys._

import com.earldouglas.xsbtwebplugin.WebPlugin.{container, webSettings}
import com.earldouglas.xsbtwebplugin.PluginKeys._

import com.typesafe.sbt.web.PathMapping
import com.typesafe.sbt.web.pipeline.Pipeline
import com.typesafe.sbt.web.Import._
import com.typesafe.sbt.less.Import.LessKeys
import com.typesafe.sbt.uglify.Import._
import com.typesafe.sbt.jshint.Import._
import net.ground5hark.sbt.concat.Import._

object BuildSettings {

  val resolutionRepos = Seq(
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
  )

  val basicSettings = Defaults.defaultSettings ++ Seq(
    name := "$name$",
    version := "$version$",
    scalaVersion := "2.11.5",
    scalacOptions := Seq("-deprecation", "-unchecked", "-feature", "-language:postfixOps", "-language:implicitConversions"),
    resolvers ++= resolutionRepos
  )

  val liftAppSettings = basicSettings ++
    webSettings ++
    addCommandAlias("ccr", "~ ;container:start ;container:reload /") ++
    addCommandAlias("ccrs", "~ ;container:start ;container:reload / ;browserSync")
}
