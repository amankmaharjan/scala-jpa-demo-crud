package controllers

import form.UserForm
import form.UserForm._
import javax.inject._
import models.UserModel
import play.api.data.Form
import play.api.db.Database
import play.api.mvc._
import play.data.FormFactory

import scala.collection.JavaConverters._


/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(db: Database, formFactory: FormFactory, components: MessagesControllerComponents)
  extends MessagesAbstractController(components) {


  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index = Action { implicit request: MessagesRequest[AnyContent] =>
    var userList = UserModel.find.all().asScala.toList;
    for (a <- userList) {
      println("User name : " + a.username);
    }
    Ok(views.html.index(userList, form))
  }


  def create = Action { implicit request =>
    val errorFunction = { formWithErrors: Form[Data] =>
      // This is the bad case, where the form had validation errors.
      // Let's show the user the form again, with the errors highlighted.
      // Note how we pass the form with errors to the template.
      var userList = UserModel.find.all().asScala.toList;
      print("inside error")
      BadRequest(views.html.index(userList, formWithErrors))
    }

    val successFunction = { data: Data =>
      // This is the good case, where the form was successfully parsed as a Data object.
      var userModel = new UserModel
      userModel.username = data.username
      userModel.password = data.password
      userModel.save
      Redirect(routes.HomeController.index());

    }

    val formValidationResult = form.bindFromRequest
    formValidationResult.fold(errorFunction, successFunction)
  }

  //
  //    print(form.bindFromRequest.get.password);
  //    print(form.bindFromRequest.get.username);
  //    val conn = db.getConnection()
  //
  //    try {
  //      val stmt = conn.createStatement();
  //
  //      val rs = stmt.execute("INSERT INTO `playdb`.`User` (`id`, `name`, `password`) VALUES (NULL, 'aman', 'adf')")
  //
  //    } finally {
  //      conn.close()
  //    }

//}


def show (id: Int) = Action {
  implicit request: MessagesRequest[AnyContent] =>
  var user = UserModel.find.ref (id)
  print ("showed user=" + user.username)
  Ok (views.html.userDetail (user, form) )

}

  def delete (id: Int) = Action {
  implicit request: MessagesRequest[AnyContent] =>
  var user = UserModel.find.ref (id)
  print ("deleted user=" + user.username)

  user.delete ()
  Redirect (routes.HomeController.index () );
}

  def edit (id: Int) = Action {
  implicit request: MessagesRequest[AnyContent] =>
  var user = UserModel.find.ref (id)

  val filledForm = form.fill (UserForm.Data (user.id, user.username, user.password) )
  Ok (views.html.edit (user, filledForm) )
}

  def update = Action {
  implicit request =>
  var userModel = new UserModel
  userModel.username = form.bindFromRequest ().get.username
  userModel.password = form.bindFromRequest ().get.password
  userModel.id = form.bindFromRequest ().get.id
  userModel.update ()
  Redirect (routes.HomeController.index () );

}


}
