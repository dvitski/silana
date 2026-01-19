@file:Suppress("PropertyName")

plugins {
    id("cc.dvitski.gradle.fabric") version "1.0.0"
}

version = "1.0.1"
group = "cc.dvitski"

fabricDsl {
    modId.set("silana")
    minecraftVersion.set("1.21.11")
    loaderVersion.set("0.18.2")
    fabricApiVersion.set("0.139.4+1.21.11")
    kotlinVersion.set("2.2.21")
    fabricKotlinVersion.set("1.13.7")
}
