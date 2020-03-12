<template>
    <v-container>
        <v-container>
            <v-card width="100%" outlined raised>
                <v-row>
                    <v-col cols="3">
                        <v-responsive class="pl-3" height="350" width="233">
                            <v-img :src="manga.image" height="350" width="233"/>
                        </v-responsive>
                    </v-col>
                    <v-col cols="6">
                        <v-card-text>
                            Author: {{manga.author}}
                        </v-card-text>
                        <v-card-text>
                            Artist: {{manga.artist}}
                        </v-card-text>
                        <v-card-text>
                            Release date: {{manga.year}}
                        </v-card-text>
                        <v-card-text>
                            Genres:
                            <span class="item" v-for="genre in manga.genres" :manga="genre" :key="genre.id">
                            {{genre.toLowerCase()}}
                        </span>
                        </v-card-text>
                        <v-card-text>
                            Status: {{manga.status.toLowerCase()}}
                        </v-card-text>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="3">
                        <v-btn class="ml-3" color="primary" type="submit" @click="deleteManga(manga)">Delete</v-btn>
<!--                        <v-btn color="primary" type="submit" @click="">Update</v-btn>-->
                        <v-btn color="primary" type="submit" @click="addChapter()">Add Chapter</v-btn>
                    </v-col>
                </v-row>
            </v-card>
        </v-container>
        <v-container>
            <v-card width="100%">
                <v-list>
                    <v-list-item v-for="chapter in chapters"
                                 :key="chapters.number"
                                 >
                        <v-list-item-content @click="linkTo(chapter.id)">
                            <v-list-item-title  v-text="chapter.name"/>
                        </v-list-item-content>
                        <v-list-item-action>
                            <v-btn icon @click="deleteChapter(chapter.id)">
                                <v-icon>remove</v-icon>
                            </v-btn>
                        </v-list-item-action>
                    </v-list-item>
                </v-list>
            </v-card>
        </v-container>
    </v-container>
</template>

<script>
    import {mapMutations, mapState, mapActions} from 'vuex'
    import axios from "axios"

    export default {
        created() { // получить упрощенную мангу без страниц, но с главами
            const current = this
            axios.get('/api/manga/' + this.$route.params.id + '/simple')
                .then(response => {
                    current.manga = response.data
                    axios.get('/api/chapter/simple/' + current.manga.id)
                        .then(chapterResponse => {
                            console.log(chapterResponse.data)
                            current.manga.chapters = chapterResponse.data
                            current.chapters = chapterResponse.data
                        })
                })
        },
        data() {
            return {
                manga: {
                    id: 1,
                    name: '',
                    author: '',
                    artist: '',
                    year: null,
                    genres: [],
                    status: '',
                    image: '',
                    chapters: []
                },
                chapters: [],
                path: '/manga/' + this.$route.params.id + '/chapter/'
            }
        },
        methods: {
            ...mapActions([
                'deleteMangaAction',
            ]),
            deleteManga(manga) {
                const cur = this
                cur.deleteMangaAction(manga)
                cur.$router.push('/')
            },
            addChapter() {
                this.$router.push('/manga/' + this.$route.params.id + '/chapter')
            },
            linkTo(index) {
                this.$router.push('/manga/' + this.$route.params.id + '/chapter/' + index)
            },
            deleteChapter(index) {
                const current = this
                axios.delete('/api/chapter/' + index).then(() => {
                    axios.get('/api/manga/' + this.$route.params.id + '/simple')
                        .then(response => {
                            current.manga = response.data
                            axios.get('api/chapter/simple')
                                .then(chapterResponse => {
                                    console.log(chapterResponse.data)
                                    current.manga.chapters = chapterResponse.data
                                })
                        })
                })
            }
        },
    }
</script>

<style scoped>

</style>