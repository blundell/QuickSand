QuickSand
=========

When you want to show a really enchanting explanatory animation to your users when they click or do something for the first time, but you know that after a while it'll get tedious and would stop users wanting to use your app.
QuickSand is here to solve that problem.

Automatically manipulates the duration of animations depending on how many times the user has viewed them.

Simple Use
----------

This is the setup needed to define how you change the animation duration between views:

```java
Map<String, Viscosity> modifiers = new HashMap<>();
modifiers.put("MyAnimationSetKey", TwoStepViscosity.defaultInstance());
Quicksand.create(this, modifiers);
```

This is you "trapping" your animation, meaning when this view is animated Quicksand will determine how many times it as been viewed and vary the animation duration accordingly: 

```java 
View myView = findViewById(R.id.my_view);
QuickSand.trap("MyAnimationSetKey", myView);
myView.animate().alpha();
```

Also works for multiple view animation:

```java
 View myView = findViewById(R.id.my_view);
 View myOtherView = findViewById(R.id.my_view);
 View myThirdView = findViewById(R.id.my_view);
 QuickSand.trap("MyAnimationSetKey", myView, myOtherView, myThirdView);
 myView.animate().alpha();
 myOtherView.animate().alpha();
 myThirdView.animate().alpha();
```

Some viscosities already defined [see the code here](https://github.com/blundell/QuickSand/tree/master/core/src/main/java/com/blundell/quicksand/viscosity):

- AllOrNothing - you can run your animation for X seconds X times and then 0 seconds after that
- LinearChange - speed up the animation each time it is viewed until X views
- TwoStep - run animation at X speed for X times then it will run at X/2

See the [demo project](https://github.com/blundell/QuickSand/tree/master/demo) for more examples.

Adding to your project
--------

```groovy
dependencies {
    compile 'com.blundell:quicksand:1.0.1'
}
```


--------

See it to believe it
--------

|15 second gif below|  
|---|
|Example of touching the image over and over to show how the animation duration decreases over time (*over the number of times the user has seen it not just from touch events*).|
|![demo](demo/demo_z1c.gif)|

Download
--------

Get [1.0.1 here](https://github.com/blundell/QuickSand/raw/master/releases/quicksand-1.0.1.aar)

License
-------

[Apache 2](LICENSE.txt)

