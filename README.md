# Not A Lib [![Build Status](https://travis-ci.org/NotAModder/NotALib.svg?branch=master)](https://travis-ci.org/NotAModder/NotALib) [![](http://cf.way2muchnoise.eu/266545.svg)](https://minecraft.curseforge.com/projects/notalib) [![](http://cf.way2muchnoise.eu/versions/266545.svg)](https://minecraft.curseforge.com/projects/notalib)
NotALib is a library mod which contains shared code for minecraft mods. This mod is intended to be used for just my mods, although other mod devs are more than welcome to use it as well. This mod is open source, under the LGPL 2.1 license, so please feel free to take advantage of that if you don't want to hard dep on this mod. 

## Building
This project is currently using CurseForge's Maven. This may change in the future. To add this project as a dependency using gradle, add the following repository to the repository list. 

```
repositories {
    maven {
        name = "CurseForge"
        url = "https://minecraft.curseforge.com/api/maven/"
    }
}
```

Once you have the repo added to your list, you can pull the library by adding the following to your dependencies. 

```
compile "notalib:NotALib:1.11.2:1.0.+"
```

To explain Curse's maven system, the first bit is the slug for the project (The name in the url), and then add the name of the file you want but replace all dashes with colons. That's the gist of it at least. 
