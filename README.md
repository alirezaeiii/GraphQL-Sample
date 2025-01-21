# GraphQL Sample
A sample which showcases Kotlin, Jetpack Compose, Hilt and [Apollo GraphQL](https://studio.apollographql.com/public/countries/) inspired by [Philipp Lackner](https://www.google.com/url?sa=t&source=web&rct=j&opi=89978449&url=https://www.youtube.com/watch%3Fv%3DME3LH2bib3g&ved=2ahUKEwjw-5zK_IeLAxWrGDQIHfSyNy0QwqsBegQICxAF&usg=AOvVaw25hDoSBPjO19O_dbNVqfoW)

## Prerequisites

You need to run this task to generate schema in the project :
```
./gradlew :app:downloadApolloSchema --endpoint=https://countries.trevorblades.com/graphql --schema=app/src/main/graphql/com/example/schema.graphqls
```

## Libraries
* [Android Jetpack](https://developer.android.com/jetpack)
   * [Compose](https://developer.android.com/jetpack/compose) Android’s modern toolkit for building native UI.
   * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) is designed to store and manage UI-related data in a lifecycle conscious way. This allows data to survive configuration changes such as screen rotations.
   * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
* [Apollo GraphQL](https://github.com/apollographql) is a query language for APIs and a runtime for fulfilling those queries with your existing data.

## Licence
    MIT License

    Copyright (c) 2025 Mohammadali Rezaei

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
