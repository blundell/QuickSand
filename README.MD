QuickSand
=========

Ever wanted to show a really cool animation to your users when they click or do something for the first time?
But you know that after a while it'll get tedious and would stop users wanting to use your app.
QuickSand is here to solve that problem.

You can manipulate the duration of your animation depending on how many times the user has viewed it.

Simple Use
----------

```java
Quicksand.create(this);

View myView = findViewById(R.id.my_view);
QuickSand.trap("MyAnimationSetKey", myView);
myView.animate().alpha().start();
```

Customising the way your animation changes it's duration:

```java
Map<String, Viscosity> modifiers = new HashMap<>();
modifiers.put("MyAnimationSetKey", TwoStepViscosity.defaultInstance());
Quicksand.create(this, modifiers);

View myView = findViewById(R.id.my_view);
QuickSand.trap("MyAnimationSetKey", myView);
myView.animate().alpha().start();
```

Download
--------

// TODO

License
-------

[Apache 2](LICENSE.txt)

