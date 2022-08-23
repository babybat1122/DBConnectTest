/*
 *  ------------------------------------------------------------------------------------
 *
 *  teszt2 project
 *
 *  Module:  teszt2 (teszt2)
 *  File:    Fact.java
 *  Created: 2022.08.23. 10:15:40
 *
 *  @Author  u320729
 *
 *  ------------------------------------------------------------------------------------
 */
package nav.gov.hu.entity;

import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author u320729
 */
@Entity
@EqualsAndHashCode
@ToString
@Named
@Table(name = "VICADINOFACTS", catalog = "", schema = "BEVFELD")
@NamedQuery(name = "Fact.findById", query = "SELECT f FROM Fact f WHERE f.id = :rnd")
public class Fact implements Serializable {

    @Id
    @Getter
    @Column(name = "ID")
    @NotNull
    public int id;

    @Getter
    @Setter
    @Column(name = "FACT")
    @NotNull
    public String fact;
}
