<template>
    <v-container >
        <v-row wrap >
            <v-col cols="12" md="6" lg="3" v-for="manga in mangaArray" :key="manga.id">
                <v-skeleton-loader :loading="loading" transition="scale-transition" height="94" type="list-item-two-line">
                    <v-card outlined raised class="text-center ma-2" height="300px" width="200px">
                        <v-responsive >
                            <v-img @click="linkTo(manga.id)" class="white--text align-end"
                                   :src="manga.image" height="300px" width="200px">
                                <v-card-title v-text="manga.name"></v-card-title>
                            </v-img>
                        </v-responsive>
                    </v-card>
                </v-skeleton-loader>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    import axios from 'axios';
    import {mapState, mapMutations, mapActions} from 'vuex'

    export default {
        data() {
          return {
              path: "/manga/",
              loading: false
          }
        },
        computed: {
            ...mapState(['mangaArray']),
        },
        methods: {
            ...mapMutations(['addMangaArrayMutation']),
            ...mapActions(['deleteMangaAction']),
            linkTo(item) {
                return this.$router.push('/manga/' + item)
            },
            deleteManga(manga) {
                this.deleteMangaAction(manga)
            }
        },
        created() {
            this.loading = true
            const current = this
            axios.get('/api/manga')
                .then(response => {
                    current.addMangaArrayMutation(response.data)
                    // current.loading = false
                })
                .catch(e => {
                    console.log(e)
                })
                .then(() => {
                    current.loading = false
                })
        }
    }
</script>

<style scoped>

</style>