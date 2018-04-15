# karbon

Instantiate data classes quickly and reliably for unit testing.

## Usage
```kotlin
data class User(
        name: String, 
        dob: Date?, 
        email: String,
        followers: List<User>,
        source: String = "organic", 
        ...
)

val user = User::class.createInstance().copy(name = "John")

println(user)
// User(name = "John", dob = null, email = "", followers = [], source = "organic", ... )
``` 

## Install

```groovy
compile "com.giljulio.karbon:karbon:0.0.1"
```

Snapshots of the development version are available in Sonatype's snapshots repository.

## License
```
Copyright (C) 2018 Gil Julio

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```