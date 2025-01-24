package com.devsuperior.dslist.entities;

import jakarta.persistence.*;

import java.util.Objects;

// O @Entity vai configurar a classe Java para que
// ela seja equivalente a uma tabela do banco relacional
@Entity
// O @Table serve para dizer qual será o nome da tabela equivalente
@Table(name = "tb_game")
public class Game {
    // O @Id serve para dizer que a
    // linha abaixo (o Long id) será a chave primária da tabela do banco
    @Id
    // O @GeneratedValue serve para que esse id seja auto-increment no banco de dados
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    // O @Column serve para alterar o nome da coluna no banco,
    // porque a palavra year é palavra reservada no SQL. Quando você não customiza o campo
    // na tabela manualmente ele mantem o mesmo nome do dado
    @Column(name = "game_year")
    private Integer year;
    private String genre;
    private String platforms;
    private Double score;
    private String imgUrl;
    private String shortDescription;
    private String longDescription;

    public Game(){

    }

    public Game(Integer year, String longDescription, String shortDescription,
                String imgUrl, Double score, String platforms,
                String genre, String title, Long id) {
        // o atributo que está com this referencia o dado do objeto,
        // o atributo sem o this referencia o que chegou como argumento no metodo
        // Basicamente diz que o year do objeto recebe o year que veio como argumento no metodo construtor
        this.year = year;
        this.longDescription = longDescription;
        this.shortDescription = shortDescription;
        this.imgUrl = imgUrl;
        this.score = score;
        this.platforms = platforms;
        this.genre = genre;
        this.title = title;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    /*@Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }*/

    // Os metodos abaixo servem para comparar dois "games" se são iguais ou não
    // O uso desses metodos é importante em classes de dados para que seja possível comparar um objeto com outro

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Game other = (Game) obj;
        return Objects.equals(id, other.id);
    }

}
