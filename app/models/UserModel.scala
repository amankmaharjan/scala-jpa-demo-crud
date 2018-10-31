package models

import finders.UserModelFinder
import io.ebean.{Finder, Model}
import javax.persistence.{Column, Entity, Id}

import scala.beans.BeanProperty

@Entity
class UserModel extends Model {

  @Id
  var id: Int = 0

  @Column
  @BeanProperty
  var username: String = null

  @Column
  @BeanProperty
  var password: String = null


}

object UserModel {
  var find = new UserModelFinder
}