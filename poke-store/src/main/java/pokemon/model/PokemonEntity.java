package pokemon.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Pokemon", schema = "Naloufi", catalog = "")
public class PokemonEntity {
    @Basic
    @Column(name = "vitesse")
    private Integer vitesse;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPokemon")
    private int idPokemon;

    public Integer getVitesse() {
        return vitesse;
    }

    public void setVitesse(Integer vitesse) {
        this.vitesse = vitesse;
    }

    public int getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(int idPokemon) {
        this.idPokemon = idPokemon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonEntity that = (PokemonEntity) o;
        return idPokemon == that.idPokemon && Objects.equals(vitesse, that.vitesse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vitesse, idPokemon);
    }
}
