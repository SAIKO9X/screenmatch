package br.com.screenmatch.model;

public enum Category {
    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime"),
    FICCAO_CIENTIFICA("Sci-Fi", "Ficção Científica"),
    TERROR("Horror", "Terror"),
    AVENTURA("Adventure", "Aventura"),
    ANIMACAO("Animation", "Animação"),
    FANTASIA("Fantasy", "Fantasia"),
    SUSPENSE("Thriller", "Suspense"),
    MISTERIO("Mystery", "Mistério"),
    SUPERHEROI("Superhero", "Super-herói"),
    DOCUMENTARIO("Documentary", "Documentário"),
    MUSICAL("Musical", "Musical"),
    HISTORICO("Historical", "Histórico"),
    GUERRA("War", "Guerra"),
    WESTERN("Western", "Faroeste"),
    ESPORTES("Sports", "Esportes"),
    ANIME("Anime", "Anime"),
    FAMILIA("Family", "Família"),
    BIOGRAFIA("Biography", "Biografia"),
    FANTASIA_CIENTIFICA("Sci-Fi Fantasy", "Fantasia Científica"),
    COMEDIA_ROMANTICA("Romantic Comedy", "Comédia Romântica"),
    THRILLER_PSICOLOGICO("Psychological Thriller", "Suspense Psicológico"),;

    private String categoryOmdb;
    private String categoryPtBr;

    Category(String categoryOmdb, String categoryPtBr) {
        this.categoryOmdb = categoryOmdb;
        this.categoryPtBr = categoryPtBr;
    }

    public static Category fromString(String text) {
        for(Category category : Category.values()) {
            if (category.categoryOmdb.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Nenhuma série de " + text + " encontrada");
    }

    public static Category fromPortuguese(String text) {
        for(Category category : Category.values()) {
            if (category.categoryPtBr.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Nenhuma constante com texto" + text + "encontrada");
    }
}