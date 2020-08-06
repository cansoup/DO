<template>
	<div>
		<v-combobox
				v-model="model"
				:filter="filter"
				:hide-no-data="!search"
				:items="items"
				:search-input.sync="search"
				hide-selected
				label="태그를 추가해주세요"
				multiple
				small-chips
				solo
				hint="태그는 최대 5개까지 가능합니다"
			>
			<template v-slot:no-data>
				<v-list-item>
					<!-- <span class="subheading">Create</span> -->
					<v-chip
						:color="`${color} lighten-3`"
						label
						small
					>
						{{ search }}
					</v-chip>
				</v-list-item>
			</template>
			<template v-slot:selection="{ attrs, item, parent, selected }">
				<v-chip
					v-if="item === Object(item)"
					v-bind="attrs"
					:color="`${item.color} lighten-3`"
					:input-value="selected"
					label
					small
				>
					<span class="pr-2">
						{{ item.text }}
					</span>
					<v-icon
						x-small
						@click="parent.selectItem(item)"
					>mdi-close</v-icon>
				</v-chip>
			</template>
			<template v-slot:item="{ index, item }">
				<v-text-field
					v-if="editing === item"
					v-model="editing.text"
					autofocus
					flat
					background-color="transparent"
					hide-details
					solo
					@keyup.enter="edit(index, item)"
				></v-text-field>
				<v-chip
					v-else
					:color="`${item.color} lighten-3`"
					dark
					label
					small
				>
					{{ item.text }}
				</v-chip>
				<v-spacer></v-spacer>
				<v-list-item-action @click.stop>
					<v-btn
						icon
						@click.stop.prevent="edit(index, item)"
					>
						<v-icon>{{ editing !== item ? 'mdi-pencil' : 'mdi-check' }}</v-icon>
					</v-btn>
				</v-list-item-action>
			</template>
		</v-combobox>
	<b-button @click="onSubmitButton">저장하기</b-button>
	</div>
</template>

<script>

export default {
	name:'TagInputBox',
	data() {
		return {
			activator: null,
			attach: null,
			// colors: ['green', 'purple', 'indigo', 'cyan', 'teal', 'orange'],
			color: 'green',
			editing: null,
			index: -1,
			items: [
				{ header: '누르면 태그가 달립니다' },
			],
			nonce: 0,
			menu: false,
			model: [
			],
			x: 0,
			search: null,
			y: 0,
		}
	},

	watch: {
		model (val, prev) {
			if (prev.length > 5) {
				return this.$nextTick(() => this.model.pop())
			}
			if (val.length === prev.length) return
				this.model = val.map(v => {
					if (typeof v === 'string') {
						v = {
							text: v,
							color: this.color,
							}
						this.items.push(v)
						// this.nonce++
						}
					return v
			})
		},
	},

	methods: {
		edit (index, item) {
			if (!this.editing) {
				this.editing = item
				this.index = index
			} else {
				this.editing = null
				this.index = -1
			}
		},
		filter (item, queryText, itemText) {
			if (item.header) return false

			const hasValue = val => val != null ? val : ''
			const text = hasValue(itemText)
			const query = hasValue(queryText)

			return text.toString()
			.toLowerCase()
			.indexOf(query.toString().toLowerCase()) > -1
		},
		onSubmitButton() {
			this.$emit('event-data', this.model)
		}
	},
}
</script>

<style>

</style>