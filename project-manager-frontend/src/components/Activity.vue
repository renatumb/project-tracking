<script lang="ts">
import axios from 'axios'
import { IoTrashOutline } from 'oh-vue-icons/icons'

export default {
  props: {
    currentProjectSelected: Object
  },

  watch: {
    currentProjectSelected(newValue, oldValue) {
      if (newValue) {
        this.getData()
      }
    }
  },

  data() {
    return {
      activities: [],
      showModalAdd: false,

      formDataNew: {
        name: '',
        description: '',
        project: {
          id: null
        }
      },
      selectedProject: {
        index: null,
        project: null
      }
    }
  },

  mounted() {
    console.log(this.currentProjectSelected)
    //this.getData()
  },

  methods: {
    getData(page = 0, size = 50) {
      axios.get(`http://localhost:9090/api/activity?page=${page}&size=${size}&projectId=${this.currentProjectSelected.id}`)
        .then(response => {
          this.activities = response.data.content
        })
    },

    removeActivity(activityId) {
      axios.delete(`http://localhost:9090/api/activity/${activityId}`)
        .then(
          response => {
            alert('ACTIVITY Deleted successfully')
            this.getData()
          }
        ).catch(
        error => {
          let resp = JSON.parse(error.request.response)
          alert(error + '\n\n' + resp.message)
          console.log(error)
        }
      )
    },

    submitForm() {
      this.formDataNew.project.id = this.currentProjectSelected.id

      axios.post(`http://localhost:9090/api/activity`, this.formDataNew)
        .then(
          response => {
            alert('Added successfully')
            this.getData()
            this.showModalAdd = false
          }
        ).catch(
        error => {
          let resp = JSON.parse(error.request.response)
          alert(error + '\n\n' + resp.message)
          console.log(error)
          this.showModalAdd = false
        }
      )
    }
  }
}
</script>

<template>
  <section class="bg-green-200 m-4 rounded-sm p-1">
    <div class="flex flex-row justify-between items-center p-2">
      <div>
        <p class="uppercase font-bold">ACTIVITIES </p>
        <p class="uppercase font-normal"> Client : {{ currentProjectSelected?.client.name }}</p>
        <p class="uppercase font-normal"> Project: {{ currentProjectSelected?.name }}</p>
      </div>
      <button class="bg-blue-400 rounded p-2 text-xs mx-1 hover:scale-105 transition-all font-semibold" @click="showModalAdd = !showModalAdd">ADD</button>
    </div>
    <ul v-if="activities.length > 0 ">
      <li v-for="(item, index) in activities" :key="index">
        <div class="flex flex-row justify-between items-center gap-2 m-3 p-2 bg-green-500 rounded-md hover:scale-105 transition-all">
          <div>
            <p class="font-semibold">{{ item.name }}</p>
            <p>{{ item.description }}</p>
          </div>
          <div>
            <span class="border mx-4 bg-cyan-400 uppercase text-xs font-semibold p-1 rounded">status: {{ item.status }}</span>
            <button class="bg-red-400 rounded p-1 text-xs mx-1" @click="removeActivity( item.id)">
              remove
            </button>
            <button class="bg-yellow-500 rounded p-1 text-xs mx-1" @click="modifyActivity( item.id)">
              modify
            </button>
          </div>
        </div>
      </li>
    </ul>
    <div v-else>
      ** No Activities for selected Project **
    </div>
  </section>

  <section class="border-2 bg-black fixed w-2/3 h-4/5 top-0  rounded-xl  opacity-90 " v-if="showModalAdd">
    <button class="absolute top-2 right-4 font-extrabold text-red-500 text-3xl border border-red-500 rounded-full p-2 opacity-100" @click="showModalAdd = !showModalAdd">X</button>
    <div class="flex flex-col justify-center  items-center gap-10 text-yellow-700">
      -
      <div>
        <span class="font-bold text-3xl">ADD NEW ACTIVITY:<br>Project: {{ currentProjectSelected.name }}</span>
      </div>
      <form @submit.prevent="submitForm" class="flex flex-col gap-3 p-6 border font-semibold">

        <label for="name">Name:</label>
        <input type="text" id="name" v-model="formDataNew.name" required>

        <label for="description">Description:</label>
        <textarea id="description" v-model="formDataNew.description" required></textarea>

        <button type="submit" class="bg-green-400 rounded p-2 text-xs font-bold">Submit</button>
      </form>
    </div>
  </section>
</template>

<style scoped>
</style>
