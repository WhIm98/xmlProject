/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author HP
 */
interface IGenericDAO<T0> {
    List<T0> getAll(String namedQuery);
    
    T0 create(T0 t);

    T0 update(T0 t);
}
