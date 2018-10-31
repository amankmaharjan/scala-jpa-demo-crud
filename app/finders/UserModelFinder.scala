package finders

import io.ebean.Finder
import models.UserModel

class UserModelFinder extends Finder[Int, UserModel](classOf[UserModel]) {
}
