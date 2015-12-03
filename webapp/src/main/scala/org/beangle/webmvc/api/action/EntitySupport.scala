/*
 * Beangle, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2005-2015, Beangle Software.
 *
 * Beangle is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Beangle is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Beangle.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.beangle.webmvc.api.action

import scala.reflect.ClassTag
import org.beangle.commons.lang.reflect.Reflections
import org.beangle.webmvc.api.annotation.ignore
import org.beangle.webmvc.api.context.Params
import org.beangle.commons.lang.Strings._
import org.beangle.commons.lang.Objects

trait EntitySupport[T] {

  val entityType: Class[T] = {
    val tClass = Reflections.getGenericParamType(getClass, classOf[EntitySupport[_]]).get("T")
    if (tClass.isEmpty) throw new RuntimeException(s"Cannot guess entity type from ${this.getClass.getName}")
    else tClass.get.asInstanceOf[Class[T]]
  }

  protected def getId(name: String): Option[String] = {
    Params.get(name + ".id").orElse(Params.get(name + "_id").orElse(Params.get(name + "Id")))
  }

  @ignore
  protected def id(name: String): String = {
    getId(name) match {
      case Some(id) => id
      case None => throw new RuntimeException(s"Cannot find ${name}.id or ${name}_id or ${name}Id parameter")
    }
  }

  /**
   * Get entity's id from shortname.id,shortnameId,id
   */
  protected final def getId[E](name: String, clazz: Class[E]): Option[E] = {
    getId(name) match {
      case Some(id) => Params.converter.convert(id, clazz)
      case None => None
    }
  }

  /**
   * Get entity's id from shortname.id,shortnameId,id
   */
  protected final def id[E](name: String, clazz: Class[E]): E = {
    getId(name, clazz).get
  }

  protected final def intId(shortName: String): Int = {
    id(shortName, classOf[Int])
  }

  protected final def longId(shortName: String): Long = {
    id(shortName, classOf[Long])
  }

  /**
   * Get entity's long id array from parameters shortname.id,shortname.ids,shortnameIds
   */
  protected final def longIds(shortName: String): List[Long] = {
    ids(shortName, classOf[Long])
  }

  /**
   * Get entity's long id array from parameters shortname.id,shortname.ids,shortnameIds
   */
  protected final def intIds(shortName: String): List[Int] = {
    ids(shortName, classOf[Int])
  }

  /**
   * Get entity's id array from parameters shortname.id,shortname.ids,shortnameIds
   */
  protected final def ids[T: ClassTag](name: String, clazz: Class[T]): List[T] = {
    var datas = Params.getAll(name + ".id", clazz)
    if (datas.isEmpty) {
      datas = Params.get(name + ".ids").orElse(Params.get(name + "Ids")) match {
        case None => List.empty[T]
        case Some(datastring) => convert(split(datastring.toString, ","), clazz).toList
      }
    }
    datas.asInstanceOf[List[T]]
  }

  private def convert[T: ClassTag](datas: Array[String], clazz: Class[T]): Array[T] = {
    if (null == datas) return null
    val newDatas = java.lang.reflect.Array.newInstance(clazz, datas.size).asInstanceOf[Array[T]]
    for (i <- 0 until datas.length) newDatas(i) = Params.converter.convert(datas(i), clazz).getOrElse(Objects.default(clazz))
    newDatas
  }

}