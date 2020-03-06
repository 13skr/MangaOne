<template>
    <v-container>
        <v-card>
            <v-container>
                <form @submit.prevent="createChapter()">
                    <v-text-field label="Name" v-model="chapter.name"/>
                    <v-text-field label="Number" v-model="chapter.number"/>
                    <v-file-input small-chips multiple show-size counter
                                  prepend-icon="add_photo_alternate"
                                  accept="image/*" label="Pages" v-model="file"/>
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
    import axios from "axios"
    import {mapMutations, mapState, mapActions} from 'vuex'

    export default {
        created() {
            const current = this
            const index = current.$route.params.id
            function reloadArray(callback) {
                axios.get('/api/manga')
                    .then(response => {
                        current.addMangaArrayMutation(response.data)
                    })
                    .catch(e => {
                        console.log(e)
                    })
                    .then(callback)
            }

            reloadArray(function () {
                current.manga = current.mangaArray.find(x => x.id === parseInt(index))
            })
        },
        data() {
            return {
                manga: null,
                chapter: {
                    name: '',
                    number: null,
                    pages: []
                },
                file: []
            }
        },
        computed: {
            ...mapState(['mangaArray'])
        },
        methods: {
            ...mapMutations([
                'addMangaArrayMutation'
            ]),
            ...mapActions([
                'updateMangaAction'
            ]),
            createChapter() {
                for (let i = 0; i < this.file.length; i++) {
                    let isLast = false
                    if (this.file.length - i === 1) {
                        isLast = true
                    }
                    this.readFile(this.file[i], i, isLast)
                }
            },
            readFile(file, index, isLast) {
                const current = this

                function fileToByte(callback) {
                    let fileReader = new FileReader()

                    fileReader.onload = e => {
                        current.chapter.pages.push({
                            image: e.target.result,
                            number: index
                        })
                        // console.log(e.target.result)
                        callback(isLast)
                    }

                    fileReader.readAsDataURL(file)
                }

                fileToByte(function (isLastFile) {
                    if (isLastFile) {
                        current.manga.chapters.push(current.chapter)
                        console.log(current.manga)
                        current.updateMangaAction(current.manga)
                        console.log('callback')
                        current.$router.push('/manga/' + current.$route.params.id)
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>