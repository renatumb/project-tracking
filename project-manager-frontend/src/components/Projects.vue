<script lang="ts">

import axios from 'axios'
import { IoTrashOutline } from 'oh-vue-icons/icons'

export default {
  emits: ['project-selected'],

  props: {
    currentClientSelected: Object
  },

  watch: {
    currentClientSelected(newValue, oldValue) {
      if (newValue) {
        this.getData()
      }
    }
  },

  data() {
    return {
      projects: [],
      showModalAdd: false,

      formDataNew: {
        name: '',
        description: '',
        client: {
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
    this.getData()
  },

  methods: {
    getData(page = 0, size = 50) {
      axios.get(`http://localhost:9090/api/project?page=${page}&size=${size}&clientId=${this.currentClientSelected.id}`)
        .then(response => {
          this.projects = response.data.content
          console.log(this.projects)
        })
    },

    removeProject(projectId) {
      axios.delete(`http://localhost:9090/api/project/${projectId}`)
        .then(
          response => {
            alert('PROJECT Deleted successfully')
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

    selectProject(index, project) {

      this.selectedProject.index = index
      this.selectedProject.project = project

       this.$emit('project-selected', project)
    },

    submitForm() {
      this.formDataNew.client.id = this.currentClientSelected.id

      axios.post(`http://localhost:9090/api/project`, this.formDataNew)
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
        <p class="uppercase font-bold">PROJECTS </p>
        <p class="uppercase font-normal"> {{ currentClientSelected.name }}</p>
      </div>
      <button class="bg-blue-400 rounded p-2 text-xs mx-1 hover:scale-105 transition-all font-semibold" @click="showModalAdd = !showModalAdd">ADD</button>
    </div>
    <ul v-if="projects.length > 0 ">
      <li v-for="(item, index) in projects" :key="index" @click="selectProject(index, item) ">
        <div class="flex flex-row justify-between items-center gap-2 m-3 p-2 bg-green-500 rounded-md hover:scale-105 transition-all  border " :class="{'border-red-700  ': ( index == this.selectedProject.index ) }">
          <div>
            <p class="font-semibold">{{ item.name }}</p>
            <p>{{ item.description }}</p>
          </div>
          <div>
            <span class="border mx-4 bg-cyan-400 uppercase text-xs font-semibold p-1 rounded">status: {{ item.status }}</span>
            <button class="bg-red-400 rounded p-1 text-xs mx-1" @click="removeProject( item.id)">
              remove
            </button>
            <button class="bg-yellow-500 rounded p-1 text-xs mx-1" @click="modifyProject( item.id)">
              modify
            </button>
          </div>
        </div>
      </li>
    </ul>
    <div v-else>
      ** No projects for selected client **
    </div>
  </section>

  <section class="border-2 bg-black fixed w-2/3 h-4/5 top-0  rounded-xl  opacity-90 " v-if="showModalAdd">
    <button class="absolute top-2 right-4 font-extrabold text-red-500 text-3xl border border-red-500 rounded-full p-2 opacity-100" @click="showModalAdd = !showModalAdd">X</button>
    <div class="flex flex-col justify-center  items-center gap-10 text-yellow-700">
      -
      <div>
        <span class="font-bold text-3xl">ADD NEW PROJECT:<br>{{ currentClientSelected.name }}</span>
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
