@file:Suppress("PropertyName")

plugins {
    id("cc.dvitski.gradle.fabric") version "2.0.0+fabric-loom.1.17"
}

version = "1.1.0"
group = "cc.dvitski"

fabricDsl {
    modId.set("silana")
    minecraftVersion.set("26.2")
    loaderVersion.set("0.19.3")
    fabricApiVersion.set("0.154.2+26.2")
    kotlinVersion.set("2.4.0")
    fabricKotlinVersion.set("1.13.12")
}
