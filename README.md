# Cost Manager

CostManager desktop application that allows its users to track their expenses. The application should allow its users the following operations:

1. Adding a new cost while specifying the category to which that cost will be added, the sum, the currency, and a small text describing that cost.
2. Adding new categories to a small list of categories that should be already defined.
3. Getting a detailed report that lists all costs in a specific period of time the user selects.
4. Removing a cost.
5. Update a cost.

## The Architecture - MVVM design pattern
* The methods we definde in IViewModel returns void, and their signature cannot include a throws clause. They should be asynchronous methods.
* The methods we defined in IModel include throws clause in their signature. That throws clause use the specific exception type that was defined for our project. -
* The implementation of the methods that were defined in IViewModel include the use of try and catch when calling methods on the IModel object, and in case of exception the catch block includes a call for showing the proper message to the user.
* Whenever the implementation of the methods that were defined in IViewModel include code that interacts with the IView, that interaction should be performed using the SwingUtilities.invokeLater() method.
* The View object holds the reference for the ViewModel object. The ViewModel object holds the references for the View object and the Model object.
* The UI developed using a LayoutManager object that controls the size and the location of every component. The only exception for this guideline can be small dialog/frame
windows (e.g. login small window).
* Developing The Unit Tests for The Model.
* Developing the model that uses the database. This model will be used on the client-side. The database managed by MySQL.
