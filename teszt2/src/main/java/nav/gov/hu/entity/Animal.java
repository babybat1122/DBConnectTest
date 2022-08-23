/*
 *  ------------------------------------------------------------------------------------
 *
 *  teszt2 project
 *
 *  Module:  teszt2 (teszt2)
 *  File:    Animal.java
 *  Created: 2022.08.18. 12:17:25
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
import javax.persistence.NamedQueries;
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
@Table(name = "VICATESZT", catalog = "", schema = "BEVFELD")
@NamedQueries({
    @NamedQuery(name = "Animal.findAll", query = "SELECT a FROM Animal a")
    , @NamedQuery(name = "Animal.findByFaj", query = "SELECT a FROM Animal a WHERE a.faj = :allfaj")
})
public class Animal implements Serializable {

    @Getter
    @Id
    @Column(name = "ID")
    @NotNull
    private Integer id;

    @Getter
    @Setter
    @NotNull
    @Column(name = "NEV")
    private String nev;

    @Getter
    @Setter
    @NotNull
    @Column(name = "FAJ")
    public String faj;

    @Getter
    @Setter
    @Column(name = "KOR")
    private Integer kor;

}
