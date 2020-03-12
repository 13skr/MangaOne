<template>
    <v-container>
        <v-card>
            <v-responsive>
                <v-img :src="chapter.pages[i].image" @click="nextPage()"/>
<!--                <v-btn  @click="nextPage()">Next page</v-btn>-->
<!--                <v-btn @click="previousPage()">Previous page</v-btn>-->
            </v-responsive>
        </v-card>
        <v-spacer/>
        <v-btn @click="previousPage()">Previous page</v-btn>
        <v-btn @click="nextPage()">Next page</v-btn>
        {{i}}
    </v-container>
</template>

<script>
    import axios from 'axios'

    export default {
        data() {
            return {
                chapter: null,
                i: 0
            }
        },
        created() {
            const current = this
            axios.get('/api/chapter/' + this.$route.params.number)
                .then(response => {
                    current.chapter = response.data
                })

        },
        methods: {
            nextPage() {
                if (this.i < this.chapter.pages.length - 1) {
                    this.i++
                }
            },
            previousPage() {
                if (this.i > 0) {
                    this.i--
                }
            }
        }
    }
</script>

<style scoped>

</style>