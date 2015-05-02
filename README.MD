QuickSand
=========

Ever wanted to show a really cool explanatory animation to your users when they click or do something for the first time?
But you know that after a while it'll get tedious and would stop users wanting to use your app.
QuickSand is here to solve that problem.

You can manipulate the duration of your animation depending on how many times the user has viewed it.

Simple Use
----------

```java
Map<String, Viscosity> modifiers = new HashMap<>();
modifiers.put("MyAnimationSetKey", TwoStepViscosity.defaultInstance());
Quicksand.create(this, modifiers);

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

Some viscosities already defined:

AllOrNothing - you can run your animation for X seconds X times and then 0 seconds after that
LinearChange - speed up the animation each time it is viewed until X views
TwoStep - run animation at X speed for X times then it will run at X/2

See the demo project for more examples.

Adding to your project
--------

// TODO

Download
--------

// TODO

License
-------

[Apache 2](LICENSE.txt)

