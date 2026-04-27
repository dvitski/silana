@file:Suppress("PropertyName")

plugins {
    id("cc.dvitski.gradle.fabric") version "2.0.0+fabric-loom.1.16"
}

version = "1.1.0"
group = "cc.dvitski"

fabricDsl {
    modId.set("silana")
    minecraftVersion.set("26.1.2")
    loaderVersion.set("0.19.2")
    fabricApiVersion.set("0.147.0+26.1.2")
    kotlinVersion.set("2.2.21")
    fabricKotlinVersion.set("1.13.7")
}
