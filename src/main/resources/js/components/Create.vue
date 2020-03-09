<template>
    <v-container>
        <v-card>
            <v-container>
                <form @submit.prevent="createManga">
                    <v-file-input show-size counter prepend-icon="photo_album"
                                  accept="image/*" label="Cover upload" v-model="file"/>
                    <v-text-field label="Name" v-model="manga.name"/>
                    <v-text-field label="Author" v-model="manga.author"/>
                    <v-text-field label="Artist" v-model="manga.artist"/>
                    <v-text-field label="Release date" v-model="manga.year"/>
                    <v-select label="Genres" :items="items" v-model="manga.genres"
                              multiple chips hint="Select genres" clearable persistent-hint/>
                    <v-radio-group column v-model="manga.status">
                        <v-radio color="blue" label="Ongoing" value="ONGOING"/>
                        <v-radio color="green" label="Completed" value="COMPLETED"/>
                    </v-radio-group>
                    <v-card-actions>
                        <v-spacer/>
                        <v-btn color="primary" type="submit">Submit</v-btn>
                        <!-- <v-btn @click="clear">Clear</v-btn>-->
                    </v-card-actions>
                </form>
            </v-container>
        </v-card>
    </v-container>
</template>

<script>
    import {mapActions} from 'vuex'

    export default {
        data() {
            return {
                manga: {
                    name: '',
                    author: '',
                    artist: '',
                    year: null,
                    genres: [],
                    status: '',
                    image: ''
                },
                items: [
                    'ACTION',
                    'ADVENTURE',
                    'COMEDY',
                    'DRAMA',
                    'FANTASY',
                    'MAGIC',
                    'SUPERNATURAL',
                    'HORROR',
                    'MYSTERY',
                    'PSYCHOLOGICAL',
                    'ROMANCE',
                    'SCIFI',
                    'CYBERPUNK',
                    'GAME',
                    'DEMONS',
                    'HAREM',
                    'JOSEI',
                    'MARTIALARTS',
                    'KIDS',
                    'HISTORICAL',
                    'ISEKAI',
                    'MILITARY',
                    'MECHA',
                    'MUSIC',
                    'PARODY',
                    'POLICE',
                    'POSTAPOCALYPTIC',
                    'SCHOOL',
                    'SEINEN',
                    'SHOUJO',
                    'SHOUJOAI',
                    'SHOUNEN',
                    'SHOUNENAI',
                    'SPACE',
                    'SPORTS',
                    'SUPERPOWER',
                    'TRAGEDY',
                    'VAMPIRE',
                ],
                file: null
            }
        },
        methods: {
            ...mapActions(['addMangaAction']),
            createManga() {
                const current = this

                function fileToByte(callback) {
                    let fileReader = new FileReader()

                    fileReader.onload = e => {
                        current.manga.image = e.target.result
                        callback()
                    }

                    fileReader.readAsDataURL(current.file)
                }

                fileToByte(function () {
                    current.addMangaAction(current.manga)
                    current.$router.push('/')
                })
            }
        }
    }
</script>

<style scoped>

</style>