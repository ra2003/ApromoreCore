package org.apromore.dao.model;

import org.springframework.beans.factory.annotation.Configurable;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * FragmentVersionDag generated by hbm2java
 */
@Entity
@Table(name = "fragment_version_dag",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"fragmentVersionId", "childFragmentVersionId", "pocketId"})
        }
)
@Configurable("fragmentVersionDag")
public class FragmentVersionDag implements Serializable {

    private Integer id;
    private String pocketId;

    private FragmentVersion fragmentVersion;
    private FragmentVersion childFragmentVersion;

    /**
     * Public Constructor.
     */
    public FragmentVersionDag() { }



    /**
     * returns the Id of this Object.
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    /**
     * Sets the Id of this Object
     * @param id the new Id.
     */
    public void setId(final Integer id) {
        this.id = id;
    }



    @Column(name = "pocketId", length = 40)
    public String getPocketId() {
        return this.pocketId;
    }

    public void setPocketId(final String pocketId) {
        this.pocketId = pocketId;
    }


    @ManyToOne
    @JoinColumn(name = "fragmentVersionId")
    public FragmentVersion getFragmentVersion() {
        return this.fragmentVersion;
    }

    public void setFragmentVersion(final FragmentVersion newFragmentVersion) {
        this.fragmentVersion = newFragmentVersion;
    }

    @ManyToOne
    @JoinColumn(name = "childFragmentVersionId")
    public FragmentVersion getChildFragmentVersion() {
        return this.childFragmentVersion;
    }

    public void setChildFragmentVersion(final FragmentVersion newChildFragmentVersion) {
        this.childFragmentVersion = newChildFragmentVersion;
    }
}