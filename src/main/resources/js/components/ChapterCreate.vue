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
            axios.get('/api/manga/' + this.$route.params.id)
                .then(response => {
                    current.manga = response.data
                    current.chapter.manga.id = current.manga.id
                })
        },
        data() {
            return {
                manga: null,
                chapter: {
                    manga: {
                        id: null
                    },
                    name: '',
                    number: null,
                    pages: []
                },
                file: [],
            }
        },
        methods: {
            ...mapActions([
                'addChapterAction',
                'addPagesAction'
            ]),
            createChapter() {
                let cur = this
                this.manga.chapters.push(this.chapter)
                cur.addChapterAction(this.manga).then(resp => {
                    for (let i = 0; i < this.file.length; i++) {
                        let isLast = false
                        if (this.file.length - i === 1) {
                            isLast = true
                        }
                        this.readFile(this.file[i], i, isLast, resp)
                    }
                })
            },
            readFile(file, index, isLast, resp) {
                const current = this

                function fileToByte(callback) {
                    let fileReader = new FileReader()

                    fileReader.onload = e => {
                        current.chapter.pages.push({
                            chapter: resp,
                            image: e.target.result,
                            number: index
                        })
                        callback(isLast)
                    }

                    fileReader.readAsDataURL(file)
                }

                fileToByte(function (isLastFile) {
                    if (isLastFile) {
                        current.manga.chapters[current.manga.chapters.length -1] = current.chapter
                        current.addPagesAction(current.manga)
                        current.$router.push('/manga/' + current.$route.params.id)
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>