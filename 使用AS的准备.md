---
title: 使用AS的准备
date: 2025-02-16 22:22:41
tags:
---

# 1.viewbinding

build.gradle.kts中

```
    android{
    ...
    buildFeatures {
        viewBinding = true

    }
    }
```

# 2.compileSdk

build.gradle.kts中compileSdk=34改成compileSdk=35

# 3.dependencies

在dependencies中添加implementation("androidx.fragment:fragment-ktx:1.3.6")
